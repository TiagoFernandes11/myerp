import axios from "axios";

export async function getShoppingCart() {
  const response = await axios.get("http://localhost:8090/api/shopping-cart", {
    headers: {
      Authorization: localStorage.getItem("token"),
    },
    params: {
      clientIdErp: localStorage.getItem("clientIdErp"),
    },
  });
  return response.data;
}

export async function addItemToShoppingCart(item, quantity = 1) {
  return await axios.post(
    "http://localhost:8090/api/shopping-cart/add-product",
    {
      name: item.name,
      productId: item.productId,
      quantity,
      clientIdErp: localStorage.getItem("clientIdErp"),
    },
    {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    }
  );
}

export async function removeItemOfShoppingCart(item, quantity = 1) {
  return await axios.post(
    "http://localhost:8090/api/shopping-cart/remove-product",
    {
      name: item.name,
      productId: item.productId,
      quantity,
      clientIdErp: localStorage.getItem("clientIdErp"),
    },
    {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    }
  );
}
