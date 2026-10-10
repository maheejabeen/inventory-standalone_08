$ports = 8081,8082,8083
foreach ($p in $ports) {
  try { Invoke-RestMethod "http://localhost:$p/inventory/info" -TimeoutSec 2 }
  catch { Write-Host "Port $p is DOWN" }
}
