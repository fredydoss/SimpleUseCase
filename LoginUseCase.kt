/**
 * Example for a simple use case with no Rx or Coroutine
 */
class LoginUseCase(
    private val authenticationRepository: AuthenticationRepository
) : BaseUseCase<LoginUseCase.Params, LoginUseCase.Result>() {

    override fun execute(params: Params, onResult: (result: Result) -> Unit) {
        authenticationRepository.login(
            email = params.email,
            password = params.password
        ) { userId: String, error: String ->
            // handle the callback from the repo and convert it to a result type
            val result = when {
                userId.isNotEmpty() -> Result.Success(userId)
                error == "BAD_CREDENTIALS" -> Result.ErrorBadCredentials
                error == "SERVER_DOWN" -> Result.ErrorServerDown
                else -> Result.ErrorUnknown
            }
            onResult.invoke(result)
        }
    }

    /**
     * The params should be present inside the UseCase class to avoid confusion with other UseCase Params
     */
    class Params(val email: String, val password: String)

    /**
     * A sealed class Result to make an exhaustive check on the results
     */
    sealed class Result {
        class Success(val userId: String) : Result()
        object ErrorBadCredentials : Result()
        object ErrorServerDown : Result()
        object ErrorUnknown : Result()
    }
}