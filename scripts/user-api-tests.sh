#!/usr/bin/env bash
#
# Manual smoke tests for the User API (UserController).
# Requires the app running locally:  ./gradlew bootRun
#
# Usage:
#   bash scripts/user-api-tests.sh
#
# On Windows PowerShell, run through Git Bash, or use "curl.exe" instead of
# the "curl" alias (which maps to Invoke-WebRequest).

set -euo pipefail

BASE_URL="${BASE_URL:-http://localhost:8080/api/users}"
# -i shows response headers (status line + Location); -s silences the progress meter.
CURL="curl -i -s -H Content-Type:application/json"

echo "=============================================="
echo " 1) CREATE user  -> expect 201 Created + Location header"
echo "=============================================="
$CURL -X POST "$BASE_URL" \
  -d '{"email":"alice@example.com","password":"secret123","name":"Alice"}'
echo -e "\n"

echo "=============================================="
echo " 2) CREATE duplicate email -> expect 409 Conflict"
echo "=============================================="
$CURL -X POST "$BASE_URL" \
  -d '{"email":"alice@example.com","password":"another","name":"Alice II"}'
echo -e "\n"

echo "=============================================="
echo " 3) GET user by id -> expect 200 OK  (change id if needed)"
echo "=============================================="
$CURL "$BASE_URL/1"
echo -e "\n"

echo "=============================================="
echo " 4) GET missing user -> expect 404 Not Found"
echo "=============================================="
$CURL "$BASE_URL/999999"
echo -e "\n"

echo "=============================================="
echo " 5) GET all users -> expect 200 OK + JSON array"
echo "=============================================="
$CURL "$BASE_URL"
echo -e "\n"

echo "=============================================="
echo " 6) DELETE user -> expect 204 No Content  (change id if needed)"
echo "=============================================="
$CURL -X DELETE "$BASE_URL/1"
echo -e "\n"

echo "=============================================="
echo " 7) DELETE missing user -> expect 404 Not Found"
echo "=============================================="
$CURL -X DELETE "$BASE_URL/999999"
echo -e "\n"
