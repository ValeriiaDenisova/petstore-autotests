package tests;

import config.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class StoreTests {

    private static final Logger logger = LoggerFactory.getLogger(StoreTests.class);

    @BeforeMethod
    public void logTestStart(Method method) {
        logger.info("========== STARTING TEST: {} ==========", method.getName());
    }

    String orderId = "1";

    @Test(description = "GET /store/inventory. Returns pet inventories by status")
    public void returnsPetInventoriesByStatus() {
        RestClient.getRequestSpec()
                .get("/store/inventory")
                .then()
                .statusCode(200);
    }

    @Test(description = "POST /store/order. Place an order for a pet")
    public void placeOrderForPet() {
        Map<String, Object> order = new HashMap<>();
        order.put("id", orderId);
        order.put("petId", orderId);
        order.put("quantity", 1);
        order.put("shipDate", "2025-07-15T12:00:00.000Z");
        order.put("status", "placed");
        order.put("complete", true);

        RestClient.getRequestSpec()
                .body(order)
                .post("/store/order")
                .then()
                .statusCode(200)
                .body("status", equalTo("placed"));
    }

    @Test(description = "POST /store/order. Place an order for a pet invalid order")
    public void placeOrderForPetInvalidOrder() {
        RestClient.getRequestSpec()
                .post("/store/order")
                .then()
                .statusCode(400);
    }

    @Test(description = "GET /store/order/{orderId}. Find purchase order by ID", dependsOnMethods = "placeOrderForPet")
    public void findPurchaseOrderByID() {
        RestClient.getRequestSpec()
                .get("/store/order/" + orderId)
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test(description = "GET /store/order/{orderId}. Find purchase order by non exist ID")
    public void findPurchaseOrderByNonExistID() {
        RestClient.getRequestSpec()
                .get("/store/order/99")
                .then()
                .statusCode(404);
    }

    @Test(description = "DELETE /store/order/{orderId}. Delete purchase order by ID", dependsOnMethods = "findPurchaseOrderByID")
    public void deletePurchaseOrderByID() {
        RestClient.getRequestSpec()
                .delete("/store/order/" + orderId)
                .then()
                .statusCode(200);
    }

    @Test(description = "DELETE /store/order/{orderId}. Delete purchase order by not found order")
    public void deletePurchaseOrderByNotFoundOrder() {
        RestClient.getRequestSpec()
                .delete("/store/order/999999")
                .then()
                .statusCode(404);
    }
}

