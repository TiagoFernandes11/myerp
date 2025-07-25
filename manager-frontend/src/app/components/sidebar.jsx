import { useRouter } from "next/navigation";

export default function SideBar() {
  const router = useRouter();

  return (
    <aside className="w-auto h-full bg-gray-900 text-white p-6 shadow-lg">
      <h1 className="text-2xl font-bold mb-6">Sidebar</h1>
      <ul className="space-y-4">
        <li className="hover:text-blue-400 cursor-pointer">
          <a href="/dashboard">Dashboard</a>
        </li>
        <li className="hover:text-blue-400 cursor-pointer">
          <a href="/settings">Settings</a>
        </li>
        <li className="hover:text-blue-400 cursor-pointer">Profile</li>
        <li
          className="hover:text-blue-400 cursor-pointer"
          onClick={() => {
            localStorage.removeItem("token");
            router.push("/login");
          }}
        >
          Logout
        </li>
      </ul>
    </aside>
  );
}
