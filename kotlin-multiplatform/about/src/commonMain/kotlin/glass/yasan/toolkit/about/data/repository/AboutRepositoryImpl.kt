package glass.yasan.toolkit.about.data.repository

import glass.yasan.toolkit.about.domain.model.Developer
import glass.yasan.toolkit.about.domain.repository.AboutRepository
import glass.yasan.toolkit.core.annotation.InternalToolkitApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@InternalToolkitApi
public class AboutRepositoryImpl : AboutRepository {

    // TODO load the data from https://yasan.glass/about.json
    public override val developer: Flow<Developer> = flowOf(Developer())

}
