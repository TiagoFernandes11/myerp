import { useRouter } from "next/navigation";
import "./index.css";

export default function FormHeader({
  entity,
  entityName,
  saveFunction,
  deleteFunction,
}) {
  const router = useRouter();

  async function performSave() {
    const response = await saveFunction(entity);
    if (response === 200 || response === 201) {
      alert("Successfully saved");
      router.push("/clients");
    } else {
      alert("Something went wrong, " + JSON.stringify(response));
    }
  }

  function performCancel() {
    router.push(`/${entityName}`);
  }

  async function performDelete() {
    if (confirm("Are you sure you want to delete client ?")) {
      if (confirm("There is no come back, are you sure ?")) {
        const response = await deleteFunction(entity);
        if (response === 200) {
          alert(`${entityName} successfully deleted`);
        } else {
          alert("Something went wrong: " + response);
        }

        router.push(`/${entityName}`);
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
