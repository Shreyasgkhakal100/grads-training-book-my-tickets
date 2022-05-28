package movie

import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.Int
import kotlin.String
import kotlin.Unit
import norm.ParamSetter
import norm.Query
import norm.RowMapper

public class GetSingleMovieParams()

public class GetSingleMovieParamSetter : ParamSetter<GetSingleMovieParams> {
  public override fun map(ps: PreparedStatement, params: GetSingleMovieParams): Unit {
  }
}

public class GetSingleMovieRowMapper : RowMapper<GetSingleMovieResult> {
  public override fun map(rs: ResultSet): GetSingleMovieResult = GetSingleMovieResult(
  id = rs.getObject("id") as kotlin.Int,
    title = rs.getObject("title") as kotlin.String,
    duration = rs.getObject("duration") as kotlin.Int?)
}

public class GetSingleMovieQuery : Query<GetSingleMovieParams, GetSingleMovieResult> {
  public override val sql: String = """
      |SELECT * FROM movies WHERE id=1;
      |""".trimMargin()

  public override val mapper: RowMapper<GetSingleMovieResult> = GetSingleMovieRowMapper()

  public override val paramSetter: ParamSetter<GetSingleMovieParams> = GetSingleMovieParamSetter()
}

public data class GetSingleMovieResult(
  public val id: Int,
  public val title: String,
  public val duration: Int?
)
