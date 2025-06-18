import { useRouter } from "next/navigation";
import "./index.css";

export default function GeneralHeader({ selectedClient }) {
  const router = useRouter();

  function createClient() {
    router.push("/clients/new");
  }

  function editClient() {
    if (selectedClient) {
      router.push("/clients/" + selectedClient.id);
    } else {
      alert("Please select a client to edit");
    }
  }

  return (
    <div id="header" className="flex flex-col w-full p-2">
      <h1 className="text-2xl">Clients: </h1>
      <div id="buttons">
        <button onClick={editClient}>Edit</button>
        <button onClick={createClient}>Create</button>
      </div>
    </div>
  );
}
