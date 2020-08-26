/**
 * A sample Repo for the blog
 */
interface AuthenticationRepository {
    fun login(email: String, password: String, callback: (success: String, error: String) -> Unit)
}