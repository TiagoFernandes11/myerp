import { useRouter } from "next/navigation";
import "./index.css";

export default function Header() {
  const router = useRouter();

  function performSave() {
    console.log("todo");
  }

  function performCancel() {
    router.back();
  }

  function performDelete() {
    console.log("todo");
  }

  return (
    <div className="header">
      <button onClick={() => performSave()}>Salvar</button>
      <button onClick={() => performCancel()}>Cancel</button>
      <button onClick={() => performSave()}>Delete</button>
    </div>
  );
}
