
package cl.duoc.japipet.di

import cl.duoc.japipet.data.repository.ClienteLocalRepository
import cl.duoc.japipet.data.repository.ClienteRepository
import cl.duoc.japipet.data.repository.MascotaLocalRepository
import cl.duoc.japipet.data.repository.MascotaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMascotaRepository(
        implementation: MascotaLocalRepository
    ): MascotaRepository

    @Binds
    abstract fun bindClienteRepository(
        implementation: ClienteLocalRepository
    ): ClienteRepository
}
