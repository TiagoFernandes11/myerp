"use client";

import "./page.css";
import GeneralHeader from "./components/client/generalHeader";
import ClientList from "./components/client/clientList";
import AuthGuard from "../security/AuthGuard";
import { useEffect, useState } from "react";
import { useSearchParams } from "next/navigation";

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
      <GeneralHeader selectedClient={selectedClient} />
      <ClientList
        selectedClient={selectedClient}
        setSelectedClient={setSelectedClient}
        page={currentPage}
        searchFilter={selectedFilter}
        searchValue={selectedFilterValue}
      />
    </AuthGuard>
  );
}
