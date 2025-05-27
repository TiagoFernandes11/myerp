export default function LoginPage() {
  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <div className="bg-gray-900 pt-20 pb-20 pr-10 pl-10">
        <h1 className="mb-2">MyERP</h1>
          <form className="flex flex-col" method="POST" action={"http://localhost:8080/api/login"}>
            <label className="mb-2">Username</label>
            <input className="mb-4" type="text" name="username" id="username" />
            <label className="mb-2">Password</label>
            <input className="mb-4" type="text" name="password" id="password" />
            <input className="bg-blue-900 mt-5" type="submit" placeholder="Login" />
          </form>
      </div>
    </div>
  );
}
