
export default function SideBar() {
  return (
    <aside className="w-auto h-screen bg-gray-900 text-white p-6 shadow-lg">
      <h1 className="text-2xl font-bold mb-6">Sidebar</h1>
      <ul className="space-y-4">
        <li className="hover:text-blue-400 cursor-pointer">Dashboard</li>
        <li className="hover:text-blue-400 cursor-pointer">Settings</li>
        <li className="hover:text-blue-400 cursor-pointer">Profile</li>
        <li className="hover:text-blue-400 cursor-pointer">Logout</li>
      </ul>
    </aside>
  );
}
