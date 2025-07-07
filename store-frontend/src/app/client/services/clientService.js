import { BASE_URL } from "@/app/constants";
import axios from "axios";

export async function create(newClient) {
  try {
    const response = await axios.post(
      `${BASE_URL}/api/customer-account/register`,
      newClient
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
