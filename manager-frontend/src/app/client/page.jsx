"use client";

import "./page.css";
import EntityList from "@/app/components/lists/List";
import AuthGuard from "../security/AuthGuard";
import { useEffect, useState } from "react";
import { useSearchParams } from "next/navigation";
import ListHeader from "@/app/components/lists/ListHeader";
import { getClients } from "./service/clientService";

export default function ClientsPage() {
  const [selectedClient, setSelectedClient] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  const [selectedFilter, setSelectedFilter] = useState("");
  const [selectedFilterValue, setSelectedFilterValue] = useState("");
  const searchParams = useSearchParams();

  useEffect(() => {
    function getQueryParams() {
      const page = searchParams.get("page");
      const filter = searchParams.get("filter");
      const value = searchParams.get("value");

      setCurrentPage(page || 0);
      setSelectedFilter(filter || "");
      setSelectedFilterValue(value || "");
    }

    getQueryParams();
  }, [searchParams]);

  return (
    <AuthGuard>
      <ListHeader
        entityName={"client"}
        filterOptions={["email", "cpf", "cellphone"]}
        selectedEntity={selectedClient}
      />
      <EntityList
        fetchEntity={getClients}
        selectedEntity={selectedClient}
        setSelectedEntity={setSelectedClient}
        page={currentPage}
        filter={selectedFilter}
        filterValue={selectedFilterValue}
      />
    </AuthGuard>
  );
}
