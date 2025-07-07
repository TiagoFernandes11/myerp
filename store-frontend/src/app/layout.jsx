"use client";

import { useState } from "react";
import Header from "./components/Header";
import "./globals.css";

import { usePathname } from "next/navigation";

export default function RootLayout({ children }) {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  if (usePathname() === "/login") {
    return (
      <html lang="en">
        <body>
          <div>{children}</div>
        </body>
      </html>
    );
  }
  return (
    <html lang="en" className="h-full w-full">
      <body className="h-full w-full flex flex-col">
        <Header isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />
        <main className="flex-grow">{children}</main>
      </body>
    </html>
  );
}
