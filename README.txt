STANDALONE VERSION: only inventory-service (no Eureka, no Gateway)
IMPORT: STS -> File -> Import -> Maven -> Existing Maven Projects
RUN: InventoryServiceApplication (8081); duplicate run config with VM args -Dserver.port=8082, then 8083
TEST: http://localhost:8081/inventory/info , 8082, 8083 ; or run test.ps1 / test.sh
Data stored in H2 file ./data/inventorydb (shared by all instances)
