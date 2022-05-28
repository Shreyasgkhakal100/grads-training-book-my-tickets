package show

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import kotlin.Int
import kotlin.String
import kotlin.Unit
import norm.ParamSetter
import norm.Query
import norm.RowMapper

public class GetAllShowsParams()

public class GetAllShowsParamSetter : ParamSetter<GetAllShowsParams> {
  public override fun map(ps: PreparedStatement, params: GetAllShowsParams): Unit {
  }
}

public class GetAllShowsRowMapper : RowMapper<GetAllShowsResult> {
  public override fun map(rs: ResultSet): GetAllShowsResult = GetAllShowsResult(
  id = rs.getObject("id") as kotlin.Int,
    title = rs.getObject("title") as kotlin.String,
    movieId = rs.getObject("movie_id") as kotlin.Int,
    startTime = rs.getObject("start_time") as java.sql.Timestamp,
    endTime = rs.getObject("end_time") as java.sql.Timestamp)
}

public class GetAllShowsQuery : Query<GetAllShowsParams, GetAllShowsResult> {
  public override val sql: String = """
      |SELECT * FROM shows
      |""".trimMargin()

  public override val mapper: RowMapper<GetAllShowsResult> = GetAllShowsRowMapper()

  public override val paramSetter: ParamSetter<GetAllShowsParams> = GetAllShowsParamSetter()
}

public data class GetAllShowsResult(
  public val id: Int,
  public val title: String,
  public val movieId: Int,
  public val startTime: Timestamp,
  public val endTime: Timestamp
)
