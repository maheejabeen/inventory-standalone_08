#!/bin/bash
for p in 8081 8082 8083; do
  curl -s -m 2 http://localhost:$p/inventory/info || echo "Port $p is DOWN"
  echo
done
