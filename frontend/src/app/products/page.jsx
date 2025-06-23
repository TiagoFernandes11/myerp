"use client";

import AuthGuard from "../security/AuthGuard";
import ProductList from "./components/productList";
import ListHeader from "./components/listHeader";
import { useEffect, useState } from "react";
import { useSearchParams } from "next/navigation";

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
        product={selectedProduct}
        selectedFilter={selectedFilter}
        searchKeyword={searchKeyword}
        setSelectedFilter={selectedFilter}
        setSearchKeyword={setSearchKeyword}
      />
      <ProductList
        selectedProduct={selectedProduct}
        setSelectedProduct={setSelectedProduct}
        currentPage={currentPage}
        selectedFilter={selectedFilter}
        searchKeyword={searchKeyword}
      />
    </AuthGuard>
  );
}
