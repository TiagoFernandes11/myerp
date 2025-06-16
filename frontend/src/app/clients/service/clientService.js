import axios from "axios";

export async function getClients() {
  try {
    const response = await axios.get("http://localhost:8080/api/client", {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    });

    return response.data;
  } catch (error) {
    console.error("Erro ao buscar clientes:", error);
    return [];
  }
}

export async function getClientById(id) {
  const response = await axios.get("http://localhost:8080/api/client/" + id, {
    headers: {
      Authorization: localStorage.getItem("token"),
    },
    validateStatus: function () {
      return true;
    },
  });
  if (response.status == 404) {
    console.error("Error trying to find client with id: " + id);
    return {};
  }

  if (response.status === 403) {
    console.error("Not authorized");
    return {};
  }
  return response.data;
}
