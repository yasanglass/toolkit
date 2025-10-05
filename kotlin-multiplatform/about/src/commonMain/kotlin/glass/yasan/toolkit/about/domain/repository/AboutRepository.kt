package glass.yasan.toolkit.about.domain.repository

import glass.yasan.toolkit.about.domain.model.Developer
import kotlinx.coroutines.flow.Flow

public interface AboutRepository {

    public fun getDeveloper(): Flow<Developer>

}
