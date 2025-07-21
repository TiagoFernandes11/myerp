"use client";

import "./page.css";

import AuthGuard from "../security/AuthGuard";

export default function StockPage() {
  return <AuthGuard></AuthGuard>;
}
