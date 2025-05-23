"use client";

import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import Header from "@/components/header";
import SideBar from "@/components/sidebar";
import { usePathname } from "next/navigation";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  
  if(usePathname() === "/login") {
    return (
      <html lang="en">
      <body className="w-full h-full">
        <div>
            {children}
        </div>
      </body>
    </html>
    );
  }

  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        {" "}
        <Header
          addresses={[
            { label: "clients", url: "/clients" },
            { label: "products", url: "/products" },
          ]}
        ></Header>
        <div className="flex min-h-screen">
          <SideBar></SideBar>
          {children}
        </div>
      </body>
    </html>
  );
}
