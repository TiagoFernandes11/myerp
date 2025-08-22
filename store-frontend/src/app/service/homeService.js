import axios from "axios";

export async function getProducts(filter, value, pageNum) {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/product?filter=${filter}&value=${value}&pageNum=${pageNum}`
    );
    return response.data;
  } catch (error) {
    console.error("Failed to fetch home data:", error);
    return [];
  }
}
