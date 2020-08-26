/**
 * Params -> The input type
 * Result -> The output type
 */
abstract class BaseUseCase<in Params, Result> {

    /**
     * Use case should contain only one public function execute() which will execute the useCase
     */
    abstract fun execute(params: Params, onResult: (result: Result) -> Unit)
}
