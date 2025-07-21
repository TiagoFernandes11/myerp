import "./index.css";

export default function Entity({ entity, isSelected, ...props }) {
  return (
    <tr
      id="entity"
      {...props}
      style={{
        backgroundColor: isSelected ? "red" : "green",
        cursor: "pointer",
      }}
    >
      {Object.entries(entity).map(([key, value]) => (
        <td key={key}>{String(value)}</td>
      ))}
    </tr>
  );
}
