import React from 'react';
import { OpponentTile } from './OpponentTile';
import './FullMatchDetails.scss';

export const FullMatchDetails = ({ match, teamName }) => {

  if (!match) return null;

  const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;

  return (
    <div className={`FullMatchDetails ${opponentTeam.toLowerCase().split(' ')[0]}`}>
      <OpponentTile match={match} teamName={teamName}/>
      <div className="match-details">
        <div>
          <h5 className="toss">
            {match.tossWinner} won the toss and decided to {match.tossDecision} first.
        </h5>
          <h5 className="mom">{match.playerOfMatch} was Man of the Match</h5>
          <p className="date">{match.date}</p>
          <p className="venue">{match.venue}, {match.city}.</p>
        </div>
        <div className="additional-details">
          <h3>First Innings</h3>
          <p>{match.team1}</p>
          <h3>Second Innings</h3>
          <p>{match.team2}</p>
          <h3>Umpires/</h3>
          <p>{match.umpire1} & {match.umpire2}</p>
        </div>
      </div>
    </div>
  );
}