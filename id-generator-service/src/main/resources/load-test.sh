set -B
# shellcheck disable=SC2034
for i in {1..100}; do
  curl -s -X 'POST' -w "\n" 'http://localhost:8080/api/identifiers'
done