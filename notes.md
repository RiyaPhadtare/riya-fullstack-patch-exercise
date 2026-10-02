# Change Summary

## Summary of changes

Fixed status filtering and task ID search, reset pagination after filter changes, prevented stale search responses, removed an artificial API delay, debounced search requests, and moved pagination into SQL. Detailed bug explanations are in `notes.md` and the handwritten notes.

## What I chose not to change

I left the app architecture and search approach intact. I did not add indexes: the text query uses leading-wildcard `LIKE`, which ordinary indexes generally cannot speed up, and the current H2 dataset is small. I left unrelated changes, including `frontend/package-lock.json`, untouched.

## Biggest remaining risk

The backend changes have not been compiled or exercised against the running app. The Maven wrapper failed in this environment and Maven is unavailable, so SQL parameter binding and pagination behavior still need runtime verification.

## Tools and AI used

Used Codex to inspect the React and Spring code, read the handwritten image notes, make the code and documentation edits, and query the local API for `api` results. Reviewed edits against the affected code paths and diffs. Attempted a Maven build; it could not start.
