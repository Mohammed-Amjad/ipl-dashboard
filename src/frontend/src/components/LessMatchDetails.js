import React from 'react';
import {Link} from 'react-router-dom';
import { TeamLogo } from './TeamLogo';
import './LessMatchDetails.scss'


export const LessMatchDetails = ({match, teamName}) =>  {

  if (!match) return null;

  const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;
  const result = teamName === match.winner;

  return (
    <div className={`LessMatchDetails ${opponentTeam.toLowerCase().split(' ')[0]}`}>
            <span className="vs">vs </span>
      <div className={result ? 'smallOpponentTile matchWon' : 'smallOpponentTile matchLost'}>
      <Link to={`/${opponentTeam}`}>
          <TeamLogo teamName={opponentTeam} />
        </Link>
          <h4 className="opponent-team-name">
            <Link to={`/${opponentTeam}`}>{opponentTeam}</Link>
          </h4>
          <h5 className="winner">{match.winner} won by {match.resultMargin} {match.result}.</h5>
      </div>
      </div>
  );
}