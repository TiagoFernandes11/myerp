import { useRouter } from "next/navigation";
import "./index.css";
import { useState } from "react";

export default function GeneralHeader({ selectedClient }) {
  const [selectedFilter, setSelectedFilter] = useState("");
  const [searchKeyword, setSearchKeyworkd] = useState("");

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
      <div id="filters">
        <select
          onChange={(e) => {
            setSelectedFilter(e.target.value);
          }}
        >
          <option value="">Select and option</option>
          <option value="name">Name</option>
          <option value="email">Email</option>
          <option value="cpf">CPF</option>
          <option value="cellphone">Cellphone</option>
        </select>
        <input
          type="text"
          name="serachKeyword"
          placeholder="Serch"
          onChange={(e) => setSearchKeyworkd(e.target.value)}
        />
        <button
          onClick={() =>
            router.push(
              "/clients?page=0&filter=" +
                selectedFilter +
                "&value=" +
                searchKeyword
            )
          }
        >
          Search
        </button>
      </div>
    </div>
  );
}
