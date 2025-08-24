import "./index.css";

export default function EntityForm({
  entity,
  setEntity,
  isNew,
  hasImage = false,
}) {
  function handleChange(fieldName, value) {
    setEntity((prev) => ({
      ...prev,
      [fieldName]: value,
    }));
  }

  const idIndication = (
    <div className="form-ro">
      <label htmlFor="entity-id">Id: </label>
      <p id="entity-id">{entity?.id}</p>
    </div>
  );

  const content = (
    <div>
      <div className="content">
        <div className="form">
          {isNew || idIndication}
          {Object.entries(entity).map(([key, value]) => {
            if (key !== "id" && key !== "image") {
              return (
                <div key={key} className="form-row">
                  <label htmlFor={key}>{key}</label>
                  <input
                    id={key}
                    type="text"
                    value={value}
                    onChange={(e) => {
                      handleChange(key, e.target.value);
                    }}
                  />
                </div>
              );
            }
            return false;
          })}
          {entity.image && (
            <div>
              <img
                src={
                  entity.image instanceof File
                    ? URL.createObjectURL(entity.image)
                    : `data:image/*;base64,${entity.image}`
                }
                alt="not found"
                width="200px"
              />
            </div>
          )}

          {hasImage && (
            <div className="form-row">
              <label htmlFor="image">Image:</label>
              <input
                id="image"
                type="file"
                accept="image/*"
                onChange={(e) => {
                  const file = e.target.files[0];
                  if (file) {
                    const reader = new FileReader();
                    reader.onload = () => {
                      const base64 = reader.result.split(",")[1];
                      handleChange("image", base64);
                    };
                    reader.readAsDataURL(file);
                  }
                }}
              />
            </div>
          )}
        </div>
      </div>
    </div>
  );
  return <div>{content}</div>;
}
