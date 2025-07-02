"use client";

import AuthGuard from "../security/AuthGuard";
import List from "@/app/components/lists/List";
import ListHeader from "@/app/components/lists/ListHeader";
import { useEffect, useState } from "react";
import { useSearchParams } from "next/navigation";
import { getProducts } from "./service/productService";

export default function ProductsPage() {
  const [selectedProduct, setSelectedProduct] = useState();
  const [searchKeyword, setSearchKeyword] = useState("");
  const [currentPage, setCurrentPage] = useState(0);
  const [selectedFilter, setSelectedFilter] = useState("");
  const searchParams = useSearchParams();

  useEffect(() => {
    const page = searchParams.get("page");
    const filter = searchParams.get("filter");
    const value = searchParams.get("value");

    setCurrentPage(page || 0);
    setSelectedFilter(filter || "");
    setSearchKeyword(value || "");
  }, [searchParams]);

  return (
    <AuthGuard>
      <ListHeader
        entityName={"product"}
        filterOptions={["id", "name", "sku", "price"]}
        selectedEntity={selectedProduct}
      />
      <List
        fetchEntity={getProducts}
        filter={selectedFilter}
        filterValue={searchKeyword}
        page={currentPage}
        selectedEntity={selectedProduct}
        setSelectedEntity={setSelectedProduct}
      />
    </AuthGuard>
  );
}
