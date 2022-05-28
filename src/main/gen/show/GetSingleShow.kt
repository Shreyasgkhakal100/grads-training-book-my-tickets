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

public class GetSingleShowParams()

public class GetSingleShowParamSetter : ParamSetter<GetSingleShowParams> {
  public override fun map(ps: PreparedStatement, params: GetSingleShowParams): Unit {
  }
}

public class GetSingleShowRowMapper : RowMapper<GetSingleShowResult> {
  public override fun map(rs: ResultSet): GetSingleShowResult = GetSingleShowResult(
  id = rs.getObject("id") as kotlin.Int,
    title = rs.getObject("title") as kotlin.String,
    movieId = rs.getObject("movie_id") as kotlin.Int,
    startTime = rs.getObject("start_time") as java.sql.Timestamp,
    endTime = rs.getObject("end_time") as java.sql.Timestamp)
}

public class GetSingleShowQuery : Query<GetSingleShowParams, GetSingleShowResult> {
  public override val sql: String = """
      |SELECT * FROM shows WHERE id=1;
      |""".trimMargin()

  public override val mapper: RowMapper<GetSingleShowResult> = GetSingleShowRowMapper()

  public override val paramSetter: ParamSetter<GetSingleShowParams> = GetSingleShowParamSetter()
}

public data class GetSingleShowResult(
  public val id: Int,
  public val title: String,
  public val movieId: Int,
  public val startTime: Timestamp,
  public val endTime: Timestamp
)
