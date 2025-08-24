import axios from "axios";

export async function getProducts(page, filter, value) {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/product?page=${page}&filter=${filter}&value=${value}`,
      {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      }
    );
    return response.data;
  } catch (error) {
    console.log("Something went wrong: ", error);
    return [];
  }
}

export async function getProductById(id) {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/product/" + id,
      {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log("Something went wrong: ", error);
    return [];
  }
}

export async function createProduct(product) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/product",
      product,
      {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      }
    );
    return response.status;
  } catch (error) {
    if (error.response) {
      return error.response.data;
    } else if (error.request) {
      return "No response from server:", error.request;
    } else {
      return "Unespected error:", error.message;
    }
  }
}

export async function updateProduct(product) {
  console.log(product);
  try {
    const response = await axios.put(
      "http://localhost:8080/api/product/" + product.id,
      product,
      {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      }
    );
    return response.status;
  } catch (error) {
    if (error.response) {
      console.log("Error from server:", error.response.data);
      return error.response.data;
    } else if (error.request) {
      return "No response from server:", error.request;
    } else {
      return "Unespected error:", error.message;
    }
  }
}

export async function deleteProduct(product) {
  try {
    const response = await axios.delete(
      "http://localhost:8080/api/products/" + product.id,
      {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      }
    );
    return response.status;
  } catch (error) {
    if (error.response) {
      console.log("Error from server: ", error.response.data);
      return error.response.data;
    } else if (error.request) {
      return "No response from server: " + error.request;
    } else {
      return "Unespected error: " + error.message;
    }
  }
}
