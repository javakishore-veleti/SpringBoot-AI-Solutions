#!/usr/bin/env sh
set -eu

THIS_DIR="$(cd "$(dirname "$0")" >/dev/null 2>&1 && pwd)"

# List compose files relative to THIS_DIR (space-separated). Add more entries as needed.
# grafana/docker-compose.yml
COMPOSE_LIST="vector-dbs/qdrant/docker-compose.yml observability/prometheus/docker-compose.yml observability/grafana/docker-compose.yml observability/jaeger/docker-compose.yml"

docker network rm spring-ai-apps-common-network || true

docker network create spring-ai-apps-common-network || true

for rel in $COMPOSE_LIST; do
  # resolve relative path to absolute under THIS_DIR unless it's already absolute
  case "$rel" in
    /*) compose_path="$rel" ;;
    *) compose_path="$THIS_DIR/$rel" ;;
  esac

  if [ ! -f "$compose_path" ]; then
    echo "Compose file not found, skipping: $compose_path" >&2
    continue
  fi

  echo "Bringing up compose file: $compose_path"
  # Run daemonized
  docker compose -f "$compose_path" up -d
done

docker ps -a