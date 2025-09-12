#!/usr/bin/env bash
# ============================================
# Deploy cooperacion-service (SIN healthcheck)
# - Compila con Maven (skipTests)
# - Construye imagen Docker y la publica
# - Actualiza servicio con Docker Compose (sin --wait)
# ============================================

set -euo pipefail
trap 'echo "❌ Error en línea $LINENO. Abortando."; exit 1' ERR

# ===== Parámetros =====
# Uso: ./deploy-cooperacion.sh [VERSION] [COMPOSE_DIR]
VERSION="${1:-v1.1.1}"
COMPOSE_DIR="${2:-$HOME/Documentos/Compose/cooperacion-service}"

REGISTRY="pablitomixweb"
APP_NAME="cooperacion-service"
IMAGE_NAME="$REGISTRY/$APP_NAME:$VERSION"

# ===== Calcular versión anterior (tolerante a 'v' y semver) =====
parse_semver() {
  local v="${1#v}"               # quita prefijo v si existe
  IFS='.' read -r MAJ MIN PAT <<<"$v"
  MAJ="${MAJ:-0}"; MIN="${MIN:-0}"; PAT="${PAT:-0}"
  echo "$MAJ" "$MIN" "$PAT"
}
read MAJ MIN PAT < <(parse_semver "$VERSION")
if [[ "$PAT" -gt 0 ]]; then
  PAT=$((PAT-1))
elif [[ "$MIN" -gt 0 ]]; then
  MIN=$((MIN-1)); PAT=9
elif [[ "$MAJ" -gt 0 ]]; then
  MAJ=$((MAJ-1)); MIN=9; PAT=9
fi
VERSION_ANTERIOR="v${MAJ}.${MIN}.${PAT}"
IMAGE_ANTERIOR="$REGISTRY/$APP_NAME:$VERSION_ANTERIOR"

echo "ℹ️  APP:              $APP_NAME"
echo "ℹ️  Versión objetivo: $VERSION"
echo "ℹ️  Imagen objetivo:  $IMAGE_NAME"
echo "ℹ️  Compose dir:      $COMPOSE_DIR"
echo

# ===== Paso 1: Compilar (Maven) =====
echo "🔧 Paso 1: mvn clean package (skipTests) ..."
cd "$(dirname "$0")"
mvn -q -DskipTests clean package

# ===== Paso 2: Build imagen =====
echo "🐳 Paso 2: docker build $IMAGE_NAME ..."
DOCKER_BUILDKIT=1 docker build -t "$IMAGE_NAME" .

# (Opcional) latest:
# docker tag "$IMAGE_NAME" "$REGISTRY/$APP_NAME:latest"

# ===== Paso 3: Push =====
echo "📤 Paso 3: docker push ..."
docker push "$IMAGE_NAME"
# docker push "$REGISTRY/$APP_NAME:latest"

# ===== Paso 4: Deploy con compose (sin --wait) =====
echo "📦 Paso 4: Deploy con docker compose en $COMPOSE_DIR ..."
cd "$COMPOSE_DIR"

# Exporta IMAGE_TAG si tu docker-compose usa ${IMAGE_TAG}
export IMAGE_TAG="$VERSION"

echo "📥 docker compose pull ..."
docker compose pull

echo "🚀 docker compose up -d ..."
docker compose up -d

# ===== Paso 5: Limpieza =====
echo "🧹 Paso 5: eliminar imagen anterior local $IMAGE_ANTERIOR (si existe) ..."
docker image rm "$IMAGE_ANTERIOR" 2>/dev/null || echo "ℹ️  No se eliminó $IMAGE_ANTERIOR (no existe/está en uso)."

# ===== Paso 6: Verificación básica =====
echo
echo "🔎 docker compose ps:"
docker compose ps

echo
echo "📜 Últimos 100 logs:"
docker compose logs --no-color --tail=100 "$APP_NAME" || true

echo
echo "✅ Deploy completado. Ejecutando: $IMAGE_NAME"
echo "💡 Logs en vivo: docker compose logs -f $APP_NAME"
