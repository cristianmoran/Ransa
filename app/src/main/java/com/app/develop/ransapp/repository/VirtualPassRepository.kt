package com.app.develop.ransapp.repository

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.model.visitors.VisitorsRequest
import com.app.develop.ransapp.model.visitors.VisitorsResponse

interface VirtualPassRepository {

    suspend fun visitorsEnty(visitorsRequest: VisitorsRequest): EventResult<VisitorsResponse>
}