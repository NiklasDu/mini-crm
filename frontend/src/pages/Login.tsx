import { useEffect } from "react";
import LoginForm from "../components/LoginForm";

function Login() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <main className="h-[calc(100vh)] flex items-center justify-center overflow-hidden bg-linear-to-br from-teal-500 to-emerald-600 dark:from-teal-800 dark:to-emerald-900">
      <LoginForm></LoginForm>
    </main>
  );
}

export default Login;
