package com.chocky_development.data.remote.mappers_remote

import com.chocky_development.data.remote.search_result.ResultsDto
import com.chocky_development.domain.models.search_result_model.ResultsModel

class SearchResultMapper {

    fun toResultModel(resultsDto: ResultsDto): ResultsModel {
        return ResultsModel(
            words = resultsDto.words
        )
    }

}