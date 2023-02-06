package com.app.develop.ransapp.repository.impl

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.model.visitors.VisitorsRequest
import com.app.develop.ransapp.model.visitors.VisitorsResponse
import com.app.develop.ransapp.repository.VirtualPassRepository
import com.app.develop.ransapp.service.EntryService
import javax.inject.Inject

class VirtualPassRepositoryImpl @Inject constructor(
    private val entryService: EntryService
) : VirtualPassRepository {

    override suspend fun visitorsEnty(visitorsRequest: VisitorsRequest): EventResult<VisitorsResponse> {
        return entryService.visitorEntry(visitorsRequest)
    }
}