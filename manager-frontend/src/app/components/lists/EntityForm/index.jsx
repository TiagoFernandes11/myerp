import "./index.css";

export default function EntityForm({ entity, setEntity, isNew }) {
  const idIndication = (
    <div className="form-ro">
      <label htmlFor="entity-id">Id: </label>
      <p id="entity-id">{entity?.id}</p>
    </div>
  );

  function handleChange(fieldName, value) {
    setEntity((prev) => ({
      ...prev,
      [fieldName]: value,
    }));
  }

  const content = (
    <div>
      <div className="content">
        <div className="form">
          {isNew || idIndication}
          {Object.entries(entity).map(([key, value]) => {
            if (key !== "id") {
              return (
                <div key={key} className="form-row">
                  <label htmlFor={key}>{key}</label>
                  <input
                    id={key}
                    type="text"
                    value={value}
                    onChange={(e) => handleChange(key, e.target.value)}
                  />
                </div>
              );
            }
            return false;
          })}
        </div>
      </div>
    </div>
  );
  return <div>{content}</div>;
}
