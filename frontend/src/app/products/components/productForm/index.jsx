import "./index.css";

export default function ProductForm({ product, setProduct }) {
  function handleChange(field, value) {
    setProduct((prev) => ({
      ...prev,
      [field]: value,
    }));
  }

  const content = (
    <div>
      <div className="form-row">
        <label htmlFor="id">Id: </label>
        <h4>{product?.id}</h4>
      </div>
      <div className="form-row">
        <label htmlFor="sku">Sku: </label>
        <input
          type="text"
          name="sku"
          value={product?.sku}
          onChange={(e) => handleChange("sku", e.target.value)}
        />
      </div>
      <div className="form-row">
        <label htmlFor="name">Name: </label>
        <input
          type="text"
          name="name"
          value={product?.name}
          onChange={(e) => handleChange("name", e.target.value)}
        />
      </div>
      <div className="form-row">
        <label htmlFor="price">Price: </label>
        <input
          type="text"
          name="price"
          value={product?.price}
          onChange={(e) => handleChange("price", e.target.value)}
        />
      </div>
    </div>
  );

  return (
    <div className="page">{product ? content : <h1>Product not found</h1>}</div>
  );
}
