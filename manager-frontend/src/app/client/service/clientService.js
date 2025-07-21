import axios from "axios";

export async function getClients(page, filter, value) {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/client?page=${page}&filter=${filter}&value=${value}`,
      {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      }
    );

    return response.data;
  } catch (error) {
    console.error("Error trying to retrieve clients:", error);
    return [];
  }
}

export async function getClientById(id) {
  const response = await axios.get(`http://localhost:8080/api/client/${id}`, {
    headers: {
      Authorization: localStorage.getItem("token"),
    },
    validateStatus: function () {
      return true;
    },
  });
  if (response.status == 404) {
    console.error("Error trying to find client with id: " + id);
    return null;
  }

  if (response.status === 403) {
    console.error("Not authorized");
    return null;
  }
  return response.data;
}

export async function createClient(client) {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/client",
      client,
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

export async function updateClient(client) {
  try {
    const response = await axios.put(
      "http://localhost:8080/api/client/" + client.id,
      client,
      {
        headers: {
          Authorization: localStorage.getItem("token"),
          "Content-Type": "application/json",
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

export async function deleteClient(client) {
  try {
    const response = await axios.delete(
      "http://localhost:8080/api/client/" + client.id,
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
      return "No response from server:", error.request;
    } else {
      return "Unespected error:", error.message;
    }
  }
}
