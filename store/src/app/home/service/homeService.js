import axios from "axios";

export async function getProducts() {
  try {
    const response = await axios.get(
      "https://localhost:8080/api/products?filter=&value=&pageNum=0"
    );
    return response.data;
  } catch (error) {
    console.error("Failed to fetch home data:", error);
    return null;
  }
}
