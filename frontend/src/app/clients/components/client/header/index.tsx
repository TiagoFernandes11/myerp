import "./index.css";

export default function Header() {
  function includeClient() {
    console.log("include client");
  }

  function alterClient() {
    console.log("alter client");
  }

  function visualizeClient() {
    console.log("visualize client");
  }

  return (
    <div id="header" className="flex flex-col w-full p-2">
      <h1 className="text-2xl">Clients: </h1>
      <div id="buttons">
        <button onClick={visualizeClient}>visualizar</button>
        <button onClick={includeClient}>include</button>
        <button onClick={alterClient}>alterar</button>
      </div>
    </div>
  );
}
