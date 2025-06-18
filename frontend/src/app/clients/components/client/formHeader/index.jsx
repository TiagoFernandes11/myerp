import { useRouter } from "next/navigation";
import "./index.css";

import {
  updateClient,
  createClient,
  deleteClient,
} from "../../../service/clientService";

export default function FormHeader({ client, isNew }) {
  const router = useRouter();

  async function performSave() {
    if (!isNew) {
      const response = await updateClient(client);
      if (response === 200) {
        alert("Successfully updated");
        router.push("/clients");
      } else {
        alert("Something went wrong, " + JSON.stringify(response));
      }
    } else {
      const response = await createClient(client);
      if (response === 201) {
        alert("Client successfully created");
        router.push("/clients");
      } else {
        alert("Something went wrong, " + response);
      }
    }
  }

  function performCancel() {
    router.push("/clients");
  }

  async function performDelete() {
    if (confirm("Are you sure you want to delete client ?")) {
      if (confirm("There is no come back, are you sure ?")) {
        const response = await deleteClient(client);
        if (response === 200) {
          alert("Client successfully deleted");
        } else {
          alert("Something went wrong: " + response);
        }

        router.push("/clients");
      }
    }
  }

  return (
    <div className="header">
      <button onClick={() => performSave()}>Salvar</button>
      <button onClick={() => performCancel()}>Cancel</button>
      <button onClick={() => performDelete()}>Delete</button>
    </div>
  );
}
