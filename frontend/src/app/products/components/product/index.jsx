import "./index.css";

export default function Product({ product, isSelected, setIsSelected }) {
  return (
    <tr
      className={isSelected ? "selected" : ""}
      style={{ cursor: "pointer" }}
      onClick={() => {
        setIsSelected(product);
      }}
    >
      <td>{product.id}</td>
      <td>{product.sku}</td>
      <td>{product.name}</td>
      <td>{product.price}</td>
    </tr>
  );
}
