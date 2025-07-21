"use client";

import { useState } from "react";
import "./index.css";
import { useRouter } from "next/navigation";

export default function ListHeader({
  entityName,
  filterOptions,
  selectedEntity,
}) {
  const [selectedFilter, setSelectedFilter] = useState("");
  const [searchKeyword, setSearchKeyword] = useState("");

  const router = useRouter();

  function createEntity() {
    router.push(`/${entityName}/new`);
  }

  function editEntity() {
    if (selectedEntity) {
      router.push(`/${entityName}/${selectedEntity.id}`);
    } else {
      alert(`Please select a ${entityName} to edit`);
    }
  }

  return (
    <div id="header" className="flex flex-col w-full p-2">
      <h1 className="text-2xl">{entityName}s:</h1>
      <div id="buttons">
        <button onClick={editEntity}>Edit</button>
        <button onClick={createEntity}>Create new</button>
      </div>
      <div id="filters">
        <select
          onChange={(e) => {
            setSelectedFilter(e.target.value);
          }}
        >
          <option value="">Select a filter</option>
          {filterOptions.map((opt) => {
            return (
              <option key={opt} value={opt}>
                {opt}
              </option>
            );
          })}
        </select>
        <input
          type="text"
          name="searchKeyword"
          placeholder="Search"
          onChange={(e) => setSearchKeyword(e.target.value)}
        />
        <button
          onClick={() => {
            router.push(
              `/${entity}?page=0&filter=${selectedFilter}&value=${searchKeyword}`
            );
          }}
        >
          Search
        </button>
      </div>
    </div>
  );
}
