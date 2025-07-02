import "./index.css";

import Entity from "../Entity";
import { useEffect, useState } from "react";

export default function EntityList({
  fetchEntity,
  selectedEntity,
  setSelectedEntity,
  page,
  filter,
  filterValue,
}) {
  const [entityList, setEntityList] = useState([
    {
      id: "",
      firstName: "",
      lastName: "",
      email: "",
      ddd: "",
      cellphone: "",
      birthday: "",
      cpf: "",
    },
  ]);

  useEffect(() => {
    async function fetchEntities(page, filter, value) {
      const entities = await fetchEntity(page, filter, value);
      if (entities[0]) {
        setEntityList(entities);
      }
    }

    fetchEntities(page, filter, filterValue);
  }, [page, filter, filterValue]);

  return (
    <div>
      <table>
        <thead>
          <tr>
            {Object.entries(entityList[0]).map(([key, value]) => (
              <th key={key}>{key}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {entityList.map((entity) => {
            return (
              <Entity
                key={entity.id}
                entity={entity}
                onClick={() => setSelectedEntity(entity)}
                isSelected={selectedEntity?.id === entity.id}
              />
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
