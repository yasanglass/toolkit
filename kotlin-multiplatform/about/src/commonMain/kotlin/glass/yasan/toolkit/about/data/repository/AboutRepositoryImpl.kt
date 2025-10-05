package glass.yasan.toolkit.about.data.repository

import glass.yasan.toolkit.about.domain.model.Developer
import glass.yasan.toolkit.about.domain.repository.AboutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

// TODO load data from https://yasan.glass/
public class AboutRepositoryImpl : AboutRepository {

    public override fun getDeveloper(): Flow<Developer> = flowOf(Developer())

}
